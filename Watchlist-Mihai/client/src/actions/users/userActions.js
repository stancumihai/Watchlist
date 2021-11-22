import axios from 'axios';
import { ACTIONS } from './types.js';

const USERS_REST_API_URL = 'http://localhost:8080/api/users';


export const get_users = () => dispatch =>{
    axios.get(USERS_REST_API_URL)
    .then(users =>
      dispatch({
        type: ACTIONS.READ_USERS,
        payload: users.data
      })
    );
}

export default get_users