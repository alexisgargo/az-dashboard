import axios from 'axios';

const getData = async () => {
    try {
      const response = await axios.get('http://localhost:8080/juego-de-tronos/personajes');
      return response.data;
    } catch (error) {
      console.error('Error getting data:', error);
      throw error;
    }
  };
  
  export default getData;