import axios from 'axios'

const ACTORS_REST_API_URL = 'http://localhost:8080/api/actors';

class ActorService {

    async getActors(){
        return await axios.get(ACTORS_REST_API_URL);
    }

    async getActorById(id){
        return await axios.get(ACTORS_REST_API_URL + `/${id}`)
    }
}

export default new ActorService();