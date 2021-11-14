import axios from 'axios'

const USERS_REST_API_URL = 'http://localhost:8080/api/users/login';

class LoginService {

    createUser(user){
        return axios.post(USERS_REST_API_URL  , user);
    }
}

export default new LoginService();