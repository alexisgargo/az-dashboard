'use client'
import {Input} from "@nextui-org/react";
import {Dropdown, DropdownMenu, DropdownTrigger, DropdownItem, Button} from "@nextui-org/react";

import { useState } from 'react'
export default function App() {
  const [value, setValue] = useState("");
  const [value1, setValue1] = useState([]);
  return (
    <div className="w-full flex flex-col gap-2 max-w-[240px]">
      <Input
        label="Email"
        placeholder="Enter your email"
        value={value}
        onValueChange={setValue}
      />
      <p className="text-default-500 text-small">Input value: {value}</p>
      <Dropdown>
          <DropdownTrigger>
            <Button variant="bordered">Open Menu</Button>
          </DropdownTrigger>
          <DropdownMenu aria-label="Link Actions">
            <DropdownItem key="home" href="/home">
              {value}
            </DropdownItem>
            <DropdownItem key="about" href="/about">
              About
            </DropdownItem>
          </DropdownMenu>
        </Dropdown>
    </div>
   
       
     
  );
}

