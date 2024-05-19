"use client";

import { Button } from "@nextui-org/button";
import {Tabs, Tab} from "@nextui-org/tabs";
import {Card, CardBody} from "@nextui-org/card";
import { Select, SelectSection, SelectItem  } from "@nextui-org/select";

import React, { useEffect } from "react";

const releases = [
  { id_release: 1, name: "DAS", version: "1.0" },
  { id_release: 2, name: "ABC", version: "2.1" },
  { id_release: 3, name: "XYZ", version: "3.2" },
  { id_release: 4, name: "AZ", version: "4.0" },
  { id_release: 5, name: "OPQ", version: "5.5" }
];

export const AdminMenuForm = () => {
  const [selectedTab, setSelectedTab] = React.useState("create");
  const [selectedRelease, setSelectedRelease] = React.useState(null);

  const handleSelectionChangeTab = (key: any) => {
    setSelectedTab(key);
  };

  const handleSelectChange = (key: any) => {
    console.log(key.size)
    if (key.size == 0) {
      setSelectedRelease(null) 
    }   
    else { setSelectedRelease(key) };
  };

  useEffect(() => {
    console.log(selectedRelease);
}, [selectedRelease]);

  return (
    <div className="flex flex-col w-full">
    <Card className="max-w-full">
      <CardBody className="flex flex-col items-stretch">
        <Tabs
          fullWidth
          size="lg"
          aria-label="Tabs form"
          selectedKey={selectedTab}
          onSelectionChange={handleSelectionChangeTab}
        >
            <Tab key="create" title="Create Release" className="text-2xl font-bold text-center p-8">
              <form className="flex flex-col gap-4">
                <div className="flex gap-2 justify-end">
                  <Button 
                  fullWidth 
                  color="primary"
                  variant="shadow"
                  size="lg"
                  >
                    Create
                  </Button>
                </div>
              </form>
            </Tab>
            <Tab key="edit" title="Edit Release" className="text-2xl font-bold text-center p-8">
              <form className="flex flex-col gap-4">
                <Select
                  labelPlacement="outside"
                  color="primary"
                  variant="faded"
                  label="Release"
                  size="lg"
                  placeholder="Select a Release"
                  className="w-full"
                  onSelectionChange={handleSelectChange}
                >
                    <SelectSection 
                        aria-label="Single selection example"
                        >
                        {releases.map((release) => (
                            <SelectItem 
                            key={release.id_release}
                            color="primary"
                            variant="flat"
                            >
                            {`${release.name} ${release.version}`}
                            </SelectItem>
                        ))}
                    </SelectSection>
                    </Select>
                    <div className="flex gap-2 justify-end">
                    <Button 
                        fullWidth 
                        color="primary"
                        variant="shadow"
                        size="lg"
                        isDisabled={!selectedRelease}
                        >
                        Edit
                    </Button>
                </div>
              </form>
            </Tab>
      </Tabs>
    </CardBody>
    </Card>
    </div>
  );
};