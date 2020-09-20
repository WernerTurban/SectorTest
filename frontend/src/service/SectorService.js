import axios from 'axios';

const SECTOR_API_URL = 'http://localhost:8081/';

class SectorService {
  getAllSectors() {
    return axios.get(`${SECTOR_API_URL}sectors`);
  }
  saveUser(user) {
    return axios.post(`${SECTOR_API_URL}user`, user);
  }
}

export default new SectorService();
