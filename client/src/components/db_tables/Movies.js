import React, {Component} from 'react'
import MovieService from '../../services/MovieService.js'


export default class Movies extends Component{

    constructor(props){
        super(props)
        this.state = {
            movies : []
        }
    }

    componentDidMount(){
        MovieService.getMovies().then((response) =>{
            this.setState({movies: response.data})
        })
    }

    render(){
        return(
            <div>
                <h1 className = "text-center">Movie List</h1>
                <table className = "table table-striped">
                    <thead>
                        <td> Movie Id </td>
                        <td> Movie Name</td>
                        <td> Movie Year</td>
                        <td> Movie Genre</td>
                        <td> Movie Director</td>
                        <td> Movie Language</td>
                    </thead>
                    <tbody>
                        {
                            this.state.movies.map(
                                movie => 
                                <tr key = {movie.id}>
                                    <td>{movie.id}</td>
                                    <td>{movie.name}</td>
                                    <td>{movie.year}</td>
                                    <td>{movie.genre}</td>
                                    <td>{movie.director}</td>
                                    <td>{movie.language}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>                 
            </div>
        )
    }
}