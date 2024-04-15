'use client'
import { useState } from 'react';
import { Button } from "@nextui-org/react";
import axios from 'axios'; 

interface Engineer {
  name: string;
  id: number;
}

const baseUrl = 'http://localhost:8080/az_dashboard/engineer';

export default function App() {
  const [engineers, setEngineers] = useState<Engineer[]>([]);
  const [name, setName] = useState('');

  const handleAddEngineer = async () => {
    try {
      const response = await axios.post(baseUrl, { name }, {
        headers: {
          'Content-Type': 'application/json'
        }
      }); // Utilizar Axios para hacer la solicitud POST
      
      if (response.status === 200) {
        const newEngineer = response.data;
        setEngineers([...engineers, newEngineer]);
        setName('');
      } else {
        console.error('Error adding engineer:', response.statusText);
      }
    } catch (error) {
      console.error('Error adding engineer:', error);
    }
  };
  

  return (
    <div>
      <input
        type="text"
        placeholder="Name"
        value={name}
        onChange={(e) => setName(e.target.value)}
      />
      <Button onClick={handleAddEngineer}>Add Engineer</Button>
     <h1>{name}</h1>
    </div>
  );
}
