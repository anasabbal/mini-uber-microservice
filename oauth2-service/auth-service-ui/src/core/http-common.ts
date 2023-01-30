import axios from 'axios';

export default axios.create({
    baseURL: "http://localhost:9091/",
    headers: {
        "Content-type": "application/json"
    }
});