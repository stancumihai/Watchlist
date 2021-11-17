import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users';

class UserService {

    async getUsers(){
        return await axios.get(USERS_REST_API_URL);
    }

    async getUserById(id){
        return await axios.get(USERS_REST_API_URL + `/${id}`)
    }
    
    async getUsersMovie(id){
        return await axios.get(USERS_REST_API_URL + `/movies/${id}`)
    }
}

export default new UserService();