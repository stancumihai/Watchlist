import React , {useState, useEffect} from "react";
import { useLocation } from "react-router-dom";
import UserService from "../../services/UserService.js"

import "./Profile.css"

export default function Profile() {
    
    const location = useLocation();     
    const myParam = parseInt(location.state.id[0]);
    const [user,setUser] = useState(null)
    const [profile, setProfile] = useState ([])

    function loadUserById(){
        UserService.getUserById(myParam + 1).then(res =>{
            setUser(res.data);
        });
    }
    
    function loadUserMovies(){
        UserService.getUsersMovie(myParam + 1).then(res =>{
            setProfile(res.data);
        })
    }

    useEffect(() =>{
        loadUserById();
        loadUserMovies();
    // eslint-disable-next-line react-hooks/exhaustive-deps
    }, [])

    return(
        (user != null & profile.length !== 0) && 
        <div className="container bootstrap snippets bootdey">
          
            <div className="row">
            <div className="profile-nav col-md-3">
                <div className="panel">
                    <div className="user-heading round">
                        <a>
                            <img src="https://bootdey.com/img/Content/avatar/avatar3.png" alt=""/>
                        </a>
                        <h1>{user.user_name}</h1>
                        <p>deydey@theEmail.com</p>
                    </div>
                </div>
            </div>
            
            <div className="profile-info col-md-9">
                <div className="panel">
                    <div className="bio-graph-heading">
                         <h1>Hello {user.user_name}</h1>
                    </div>
                    <div className="panel-body bio-graph-info">
                        <h1>Details</h1>
                        <div className="row">
                            <div className="bio-row">
                                <p><span>First Name </span>: Camila</p>
                            </div>
                            <div className="bio-row">
                                <p><span>Last Name </span>: Smith</p>
                            </div>
                            <div className = "bio-row">
                                {
                                     profile.map((movie) => (
                                        <div>
                                            <ul>
                                                <li>{movie.name}</li>
                                                <ul>
                                                    <li>{movie.year}</li>
                                                    <li>{movie.genre}</li>
                                                    <li>{movie.director}</li>
                                                    <li>{movie.language}</li>
                                                </ul>
                                            </ul>
                                        </div>
                                    ))
                                }
                            </div>
                        </div>
                    </div>
                </div>
            <div>
      </div>
  </div>
</div>
</div>)
}

