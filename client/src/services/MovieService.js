import axios from 'axios'

const MOVIES_REST_API_URL = 'http://localhost:8080/api/movies';

class MovieService {

    async getMovies(){
        return await axios.get(MOVIES_REST_API_URL);
    }

    async getMovieById(id){
        return await axios.get(MOVIES_REST_API_URL + `/${id}`)
    }

    async getActorsByMovie(id){
        return await axios.get(MOVIES_REST_API_URL + `/actors/${id}`)
    }
}

export default new MovieService();