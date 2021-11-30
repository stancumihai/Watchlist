import React , {useEffect, useState} from 'react'
import { Card , CardMedia ,CardContent , Typography} from '@material-ui/core'
import {makeStyles } from '@material-ui/core/styles';
import { Link } from 'react-router-dom';
import CinemaService from '../../../services/CinemaService.js';

export const CinemaList = () => {
    const useStyles = makeStyles((theme) => ({
        card: {
          width: "250px",   
          height : "55",
          margin: 20,
          display: "inline-block",
          background: "rgb(30,30,30)",
          color: "white",
          position : 'relative'
        },
        container: {
          width: "100%",
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
        },
        button:{
          color:"cornflowerblue",
          position : "absolute",  
          bottom : 0
        },
        inputRoot: {
          color: 'inherit',
        },
        inputInput: {
          padding: theme.spacing(1, 1, 1, 0),
          paddingLeft: `calc(1em + ${theme.spacing(4)}px)`,
          transition: theme.transitions.create('width'),
          width: '100%',
          [theme.breakpoints.up('sm')]: {
            width: '20ch',
            '&:focus': {
              width: '30ch',
            },
          },
        }
      }));

    const classes = useStyles();
    const [cinemas, setCinemas]  = useState([])
    
    useEffect(() =>{
        CinemaService.getCinemas().then(res => setCinemas(res.data))
    },[])
    
    return (
        <div style = {{background : "#333333"}}>
            {cinemas.map(cinema =>
                <Card id =  "cinema-card" className={classes.card} key={cinema.id}>
                    <CardMedia className={classes.media} image = {cinema.url} title={ cinema.name } />
                    <CardContent>
                    <Link to={ "/cinema/" + cinema.id } color="inherit" style={{textDecoration: "none"}}>
                      <Typography gutterBottom variant="h5" component="h2">
                      {cinema.name}
                      </Typography>
                      </Link>
                      <Typography gutterBottom variant="h5" component="h2">
                        Capacity: {cinema.capacity}
                      </Typography>
                    </CardContent>
                </Card> 
            )}
        </div>
    )
}

export default CinemaList;
