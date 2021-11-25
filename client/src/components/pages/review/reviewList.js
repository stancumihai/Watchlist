import React, { useEffect, useState } from "react";
import { makeStyles } from '@material-ui/core/styles';
import Rating from '@material-ui/lab/Rating';
import {Typography , Avatar ,ListItem, List} from '@material-ui/core';
import MovieService from "../../../services/MovieService.js";
import UserService from "../../../services/UserService.js";

const useStyles = makeStyles((theme) => ({
  heading: {
    fontSize: 30,
    color: "white",
    marginTop: 45,
    marginLeft: 15
  },
  text: {
    fontSize: 20,
    margin: 15,
    color: "white"
  },
  poster: {
    maxWidth: 50
  },
  avatar: {
    margin: 25
  },
  box: {
    fontSize: 20,
    margin: 15,
    [theme.breakpoints.down('xs')]: {
      display: "none"
    }
  }
}));

export default function ReviewList(props) {
  const classes = useStyles();

  // eslint-disable-next-line no-unused-vars
  const [reviews, setReviews] = useState([]);
  // eslint-disable-next-line no-unused-vars
  const [id, setId] = useState(0)
  useEffect(() => {
    if (props.type === 'user'){
      let dummyId = JSON.parse(localStorage.getItem('state')).id
      setId(parseInt(dummyId.substring(0, id.length - 1)) + 1)
      UserService.getReviewsByUser(id + 1).then(res => setReviews(res.data))
    }else if (props.type === 'movie'){
      MovieService.getReviewsByMovie(props.movie_id).then(res => setReviews(res.data))
    }
  // eslint-disable-next-line react-hooks/exhaustive-deps
  },[])

  return (
    (reviews != null) && 
    <div style = {{background : "#333333"}}>
    <Typography className={classes.heading}>Reviews</Typography>
    <List component="nav" className={classes.list}>
    {reviews.map( review => (
      <ListItem key = {review.id} button>
        <Avatar alt="PopCritic"  className={classes.avatar} />
        <Typography className={classes.text}> {review.movie.name}</Typography>
        <Typography className={classes.text}> {review.body}</Typography>
        <Typography className={classes.text}><Rating readOnly value={ parseInt(review.rating) }/></Typography>
      </ListItem>
    ))}
    </List>
    </div>
  );
}
