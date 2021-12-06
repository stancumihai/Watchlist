import React , {useState, useEffect} from "react";
import AppBar from '@material-ui/core/AppBar';
import {Toolbar, Typography , Avatar} from '@material-ui/core';
import {makeStyles } from '@material-ui/core/styles';
import GitHubIcon from '@material-ui/icons/GitHub';
import { Link } from "react-router-dom";
import { useHistory } from "react-router";

const useStyles = makeStyles((theme) => ({
  header: {
    flexGrow: 1,
  },
  bar: {
    background: 'rgb(30,30,30)'
  },
  title: {
    flexGrow: 1,
    display: 'none',
    fontSize: 25,
    fontWeight: "bolder",
    [theme.breakpoints.up('sm')]: {
      display: 'block',
    },
  },
  login: {
    margin: 20,
    fontWeight: "bolder",
    [theme.breakpoints.down('sm')]: {
      padding: 5,
    },
  },
  search: {
    position: 'relative',
    borderRadius: 20,
    // backgroundColor: fade(theme.palette.common.white, 0.15),
    '&:hover': {
      // backgroundColor: fade(theme.palette.common.white, 0.25),
    },
    marginLeft: 0,
    width: '100%',
    [theme.breakpoints.up('sm')]: {
      marginLeft: theme.spacing(1),
      width: 'auto',
    },
  },
  searchIcon: {
    padding: theme.spacing(0, 2),
    height: '100%',
    position: 'absolute',
    pointerEvents: 'none',
    display: 'flex',
    alignItems: 'center',
    justifyContent: 'center',
  },
  inputRoot: {
    color: 'inherit',
  },
  avatar: {
    marginRight: 20,
    border: "2px solid white"
  },
  user: {
    margin: 25
  },
  gh: {
    color: "white",
    margin: 15,
    [theme.breakpoints.down('sm')]: {
      display: "none"
    }
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
  },
}));

export default function SearchAppBar() {

  const classes = useStyles();
  const [id,setId] = useState(0)

  useEffect(()=>{
    if(localStorage.state != null){
      setId(parseInt(JSON.parse(localStorage.state).id[0]) + 1)
    }
   // eslint-disable-next-line react-hooks/exhaustive-deps
  },[id])

  const history = useHistory()
  const logoutUser = () => {
    localStorage.clear();
    history.push("/", );
    window.location.reload();
  }

  return (
    <div className={classes.header}>
      <AppBar position="static" className = {classes.bar}>
        <Toolbar>
          <Link to = "/" ><Avatar alt="PopCritic" src="/header.png" className={classes.avatar} /> </Link>
          <Typography className={classes.title} variant="h6" noWrap>
            PopCritic
          </Typography>
          <a href = "https://github.com/stancumihai/Watchlist">
            <GitHubIcon fontSize="large" className={classes.gh} /></a>
            {id === 0 ? 
            <div>
              <Link to = "/"> <span>Login</span> </Link>
              <Link to = "/Register"> <span>Register</span></Link>
            </div> : 
            <div onClick={() => logoutUser()}>
              <Link to = '/' onClick = {() => {localStorage.clear()}}><span>Logout</span></Link>
            </div>
            }
         </Toolbar>
      </AppBar>
    </div>
  );
}