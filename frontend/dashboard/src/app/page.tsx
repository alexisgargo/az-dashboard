'use client'
import { useState } from 'react';
import {  Avatar, Button, NextUIProvider } from "@nextui-org/react";
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
    
    </div>
  </NextUIProvider>
  );
}
