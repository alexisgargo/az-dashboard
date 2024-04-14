'use client'
import { Input } from "@nextui-org/react";
import { Button } from "@nextui-org/react";
import { useState } from 'react'
export default function App() {
  const [value, setValue] = useState("");
 
  return (
    <div >
      <Input
        label="Engeenir"
        placeholder="Enter your Engeenir"
        value={value}
        onValueChange={setValue}
      />
      <div className="inline-block relative w-64">
        <select className="block appearance-none w-full bg-white border border-gray-400 hover:border-gray-500 px-4 py-2 pr-8 rounded shadow leading-tight focus:outline-none focus:shadow-outline">
          <option>Engeenirs</option>
          <option>Option 2</option>
          <option>Option 3</option>
        </select>

      </div>

    </div>



  );
}

