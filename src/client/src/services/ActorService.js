import axios from 'axios'

const ACTORS_REST_API_URL = 'http://localhost:8080/api/actors';

class ActorService {

    getActors(){
        return axios.get(ACTORS_REST_API_URL);
    }
}

export default new ActorService();