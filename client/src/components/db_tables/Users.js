import React, {Component} from 'react'
import UserService from '../../services/UserService.js'


export default class User extends Component{

    constructor(props){
        super(props)
        this.state = {
            users : []
        }
    }

    componentDidMount(){
        UserService.getUsers().then((response) =>{
            this.setState({users: response.data})
        })
    }


    render(){
        return(
            <div>
                <h1 className = "text-center">Users List</h1>
                <table className = "table table-striped">
                    <thead>
                        <td> User Id </td>
                        <td> User Username</td>
                        <td> User Password</td>
                    </thead>
                    <tbody>
                        {
                            this.state.users.map(
                                user => 
                                <tr key = {user.id}>

                                    <td>{user.id}</td>
                                    <td>{user.user_name}</td>
                                    <td>{user.password}</td>
                                </tr>
                            )
                        }
                    </tbody>
                </table>                 
            </div>
        )
    }
}