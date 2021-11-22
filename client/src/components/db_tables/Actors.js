import React, {Component} from 'react'
import ActorService from '../../services/ActorService.js'


export default class Actors extends Component{

    constructor(props){
        super(props)
        this.state = {
            actors : []
        }
    }

    componentDidMount(){
        ActorService.getActors().then((response) =>{
            this.setState({actors: response.data})
        })
    }

    render(){
        return(
            <div>
                <h1 className = "text-center">Actors List</h1>
                <table className = "table table-striped">
                    <thead>
                        <td> Actor Id </td>
                        <td> Actor Name</td>
                        <td> Actor Surname</td>
                        <td> Actor BirthDate</td>
                    </thead>
                    <tbody>
                        {
                            this.state.actors.map(
                                actor => 
                                <tr key = {actor.id}>
                                    <td>{actor.id}</td>
                                    <td>{actor.user_name}</td>
                                    <td>{actor.password}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>                 
            </div>
        )
    }
}