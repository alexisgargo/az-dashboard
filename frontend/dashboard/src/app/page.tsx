'use client'
import { useState, useEffect } from 'react';
import { Avatar, Button, NextUIProvider } from "@nextui-org/react";
import axios from 'axios';

interface Engineer {
  name: string;
  id: number;
  
}
interface Description {
  
  description: string;
  
}
const baseUrl = 'http://localhost:8080/az_dashboard/engineer';

export default function App() {
  const [engineers, setEngineers] = useState<Engineer[]>([]);
  const [name, setName] = useState('');
  const [descriptions, setDescriptions] = useState<Description[]>([]);
  const [description, setDescription] = useState('');

  const handleAddEngineer = async () => {
    try {
      const response = await axios.post(baseUrl, { name }, {
        headers: {
          'Content-Type': 'application/json'
        }
      });

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

  const fetchDescriptions = async () => {
    try {
      const response = await axios.get(`http://localhost:8080/az_dashboard/issues`);
      if (response.status === 200) {
        setDescriptions(response.data);
      } else {
        console.error('Error fetching descriptions:', response.statusText);
      }
    } catch (error) {
      console.error('Error fetching descriptions:', error);
    }
  };

  useEffect(() => {
    fetchDescriptions();
  }, []);

  return (
    <NextUIProvider>
      <div className="w-72">
        <div className="relative w-full min-w-[200px] h-10">
          <input
            type="text"
            placeholder="Name"
            value={name}
            onChange={(e) => setName(e.target.value)}
            className="max-w-[220px]"
          />
          <Button onClick={handleAddEngineer}>Add Engineer</Button>
          <h1>{name}</h1>
        </div>
        <div>
          <h2>Descriptions:</h2>
          <Button onClick={fetchDescriptions}>get issue descriptions</Button>
          <ul>
            {descriptions.map((desc, index) => (
              <li key={index}>{desc.description}</li>
            ))}
          </ul>
        </div>
      </div>
    </NextUIProvider>
  );
}

