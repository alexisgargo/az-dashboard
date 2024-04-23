import { Dayjs } from 'dayjs'; 
import axios from 'axios';

const getIssues = async (date: Dayjs, id_release: string) => {
    try {
      const response = await axios.get(`http://localhost:8080/az_dashboard/issues/${date.format('YYYY-MM-DD')}/${id_release}`);
      console.log(response.data);
    //   return response.data
    } catch (error) {
      console.error('Error fetching issues:', error);
    }
  };