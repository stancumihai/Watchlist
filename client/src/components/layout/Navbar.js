import React from "react";
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import { alpha, makeStyles } from '@material-ui/core/styles';
import GitHubIcon from '@material-ui/icons/GitHub';
import Avatar from '@material-ui/core/Avatar';
import Link from '@material-ui/core/Link';

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
    backgroundColor: alpha(theme.palette.common.white, 0.15),
    '&:hover': {
      backgroundColor: alpha(theme.palette.common.white, 0.25),
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

  return (
    <div className={classes.header}>
      <AppBar position="static" className = {classes.bar}>
        <Toolbar>
          <Link to="/"><Avatar alt="PopCritic" src="/header.png" className={classes.avatar} /> </Link>
          <Typography className={classes.title} variant="h6" noWrap>
            PopCritic
          </Typography>
          <Link href="https://github.com/stancumihai/Watchlist">
            <GitHubIcon fontSize="large" className={classes.gh} /></Link>
          <Link to = "/"><span>Login</span></Link>
          <Link to = "/Register"> <span>Register</span></Link>
         </Toolbar>
      </AppBar>
    </div>
  );
}