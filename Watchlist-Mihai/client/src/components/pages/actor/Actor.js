import React, { useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import CircularProgress from '@material-ui/core/CircularProgress';
import Box from '@material-ui/core/Box';
import Typography from '@material-ui/core/Typography';
import List from '@material-ui/core/List';
import Link from '@material-ui/core/Link';
import ActorService from "../../../services/ActorService.js";

const useStyles = makeStyles((theme) => ({
  title: {
  	color: "white",
  	paddingTop: 10
  },
  prof: {
  	color: "lightgrey"
  },
  plot: {
  	color: "white",
  	paddingTop: 10
  },
  button: {
  	margin: 10,
  	fontWeight: "bolder"
  },
  poster: {
    maxWidth: 250,
    margin: 20,
    display: "inline-block",
    [theme.breakpoints.down('xs')]: {
      maxWidth: "80%"
    }
  },
   box: {
    [theme.breakpoints.down('xs')]: {
      flexWrap: "wrap"
    }
  },
  list: {
    display: 'inline-block'
  },
  movies: {
    maxWidth: 150,
    margin: 15,
    [theme.breakpoints.down('xs')]: {
      maxWidth: 60
    }
  }
}));


export default function Actor() {
  const [actor, setActor] = useState(0);
  const [actorId,setActorId] = useState(0)
  const [actorMovies, setActorMovies]  = useState([])
  const classes = useStyles();
  
  useEffect(() => {
    graspActorId()
    console.log(actorMovies);
    ActorService.getMoviesByActor(actorId).then(res => setActorMovies(res.data))
    // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])
  
  const graspActorId = () =>{
    let id = '';
    for ( var char in window.location.href){
      if(char >= 28 && char !== undefined ){
        id += window.location.href[char];
      }
    }
    setActorId(parseInt(id))
    ActorService.getActorById(id).then(res => setActor(res.data))
  }

  return (
    (actor != null & actorId != null) && 
  	<div style ={{background:'black'}}>
  	<CircularProgress style={{ display: actor?"none":"block", margin: "20px auto" }} />
  	<Box display="flex" className={classes.box} justifyContent="flex-start" m={1} p={1}>
    	  <Box p={1}>
          <img className={classes.poster} src = {actor.url} alt= 'Loading'/>
        </Box>
        <Box p={1}>
          <Typography variant="h3" gutterBottom className={classes.title}>{ actor.name } {actor.surname}</Typography>
          {/* <Typography variant="h6" gutterBottom className={classes.prof}>{ actor.profession}</Typography> */}
        </Box>
  	</Box>
    <Box display="flex" className={classes.box} justifyContent="flex-start" m={1} p={1}>
      <Box p={1}>
        <Typography variant="h4" gutterBottom className={classes.title}>Movies:</Typography>
        <List className={classes.list}>
          {actorMovies.map(movie=>(<Link href={"/movie/"+movie.id}>
            <img className={classes.movies} src={movie.url} alt = 'loading'/></Link>))}
        </List>
      </Box>
    </Box>
    </div>
  )
}