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

    async addMovie(userId,movieId){
        return await axios.post(USERS_REST_API_URL + `/movies/${userId}/${movieId}`)
    }

    async getReviewsByUser(id){
        return await axios.get(USERS_REST_API_URL + `/reviews/${id}`)
    }
}

export default new UserService();