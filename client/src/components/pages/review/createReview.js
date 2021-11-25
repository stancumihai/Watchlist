/* eslint-disable react-hooks/exhaustive-deps */
import React, { useState , useEffect} from "react";
import { makeStyles } from '@material-ui/core/styles';
import Typography from '@material-ui/core/Typography';
import TextareaAutosize from '@material-ui/core/TextareaAutosize';
import Rating from '@material-ui/lab/Rating';
import Button from '@material-ui/core/Button';
import ReviewService from '../../../services/ReviewService.js'

const useStyles = makeStyles((theme) => ({
  heading: {
  	fontSize: 30,
  	color: "white",
  	margin: 15
  },
  rating: {
  	margin: "0px 0px 20px 20px"
  },
  reviewBox: {
  	maxWidth: 500,
  	fontSize: 20,
  	fontWeight: "bolder",
  	width: "250%",
  	background: "rgb(30,30,30)",
  	border: "none",
  	padding: 15,
  	color: "white",
  	borderRadius: 30,
    letterSpacing: 3,
    wordSpacing: 7,
  	[theme.breakpoints.down('sm')]: {
  		width: "80%"
  	},
  	'&:focus': {
      outline: "none"
    }
  },
  postButton: {
  	background: "transparent",
  	border: "2px solid white",
  	color: "white",
  	fontWeight: "bolder",
  	borderRadius: 17
  },
  disabledButton: {
    borderColor: "white",
    color:"white"
  }
}));


export default function CreateReview(props) {
  const classes = useStyles();
  const [body, setBody] = useState("");
  const [rating, setRating] = useState(5);
  const [userId,setUserId] = useState(0)
  const [movieId, setMovieId] = useState(0)

  useEffect(() =>{
    let id = JSON.parse(localStorage.getItem('state')).id
    setUserId(parseInt(id.substring(0,id.length - 1)) + 1);
    setMovieId(props.movie_id)
  },[])

  const postReview = (body, rating) => {
    
    let review = {
      body,
      rating
    }
    ReviewService.createReview(review, userId,movieId)
    .then(() => {
      setTimeout(() =>{
        alert("Review has registered")
      }, 1000)
    })
    .catch(err => {
      if(err.response){
        if(err.response.status === 404){
          alert('Error page not found')
        }else{
          alert('Choose different user name')
          window.location.reload()
        }
      }
    })
  }

  return (
  	<div>
        <Typography 
            className={classes.heading}>Post Review</Typography>
        <Rating button value={rating} onChange={ (e,rtg) => setRating(rtg) } className={classes.rating} />
       
        <TextareaAutosize 
            value={body} 
            onChange={ (e) => setBody(e.target.value) } maxLength={300} 
            className={classes.reviewBox}/>
        
        <Button
            onClick={ () => postReview(body,rating) } 
            className={classes.postButton} 
            classes={{ disabled: classes.disabledButton }} 
            disabled={!body.length>0}>Post Review
        </Button>
  	</div>
  )
}