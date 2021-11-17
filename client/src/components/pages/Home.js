import React, { useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import Card from '@material-ui/core/Card';
import CardContent from '@material-ui/core/CardContent';
import CardMedia from '@material-ui/core/CardMedia';
import Typography from '@material-ui/core/Typography';
import Rating from '@material-ui/lab/Rating';
import CircularProgress from '@material-ui/core/CircularProgress';
import Link from '@material-ui/core/Link';
import { useLocation } from "react-router-dom";
import { useHistory} from "react-router";

const useStyles = makeStyles((theme) => ({
  card: {
    maxWidth: 250,
    margin: 20,
    display: "inline-block",
    background: "rgb(30,30,30)",
    color: "white",
    [theme.breakpoints.down('xs')]: {
      maxWidth: "100%",
      marginLeft: 30,
      marginRight: 30
    }
  },
  container: {
    width: "90%",
    margin: "auto"
  },
  media: {
    height: 375,
    filter: "brightness(0.7)"
  },
  plot: {
    letterSpacing: 2,
    color: "lightgrey",
    marginTop: 10,
    marginBottom: 15
  }
}));

export default function Home() {
  const classes = useStyles();
  const location = useLocation();     
  const history = useHistory()

  const [movies, setMovies] = useState(0);
  const user = location.state;
  useEffect(() => {
    fetch("https://popcritic.herokuapp.com/movies").then(resp => resp.json()).then((data) => setMovies(data));
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])

  function move_profile (){
    console.log(user.user.id);
    history.push('/profile' , { id : user.user.id}) 
  } 

  return (
   <div className={classes.container}>
   <button onClick = {move_profile}> Move Back to Profile</button>
   <CircularProgress style={{ display: movies?"none":"block", margin: "20px auto" }} />
   { movies?movies.map(movie =>
    <Card className={classes.card} key={movie.movie_id}>
        <CardMedia className={classes.media} image ={ movie.poster } title={ movie.title } />
        <CardContent>
          <Link href={ "/movie/" + movie.movie_id } color="inherit" style={{textDecoration: "none"}}>
          <Typography gutterBottom variant="h5" component="h2">
          { movie.title }
          </Typography>
          </Link>
          <Typography variant="body2" component="p" className={classes.plot}>
          { movie.plot.slice(0,100) + "..." }
          </Typography>
          <Rating readOnly value={5} />
        </CardContent>
    </Card>
   ):""}
   </div>
  );
}
