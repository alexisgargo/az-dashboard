'use client'
import { useState } from 'react';
import { Button } from "@nextui-org/react";

interface Engineer {
  name: string;
  id: number;
}

const baseUrl = 'http://localhost:8080/az_dashboard/engineers';

export default function Page() {
  const [engineers, setEngineers] = useState<Engineer[]>([]);
  const [name, setName] = useState('');
  const [id, setId] = useState('');

  const handleAddEngineer = async () => {
    try {
      const response = await fetch(baseUrl, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ name, id })
      });
  
      if (response.ok) {
        const newEngineer = await response.json();
        setEngineers([...engineers, newEngineer]);
        setName('');
        setId('');
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
      <input
        type="text"
        placeholder="ID"
        value={id}
        onChange={(e) => setId(e.target.value)}
      />
      <Button onClick={handleAddEngineer}>Add Engineer</Button>
     
    </div>
  );
}
