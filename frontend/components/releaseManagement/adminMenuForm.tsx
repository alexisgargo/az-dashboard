"use client";

import { Button } from "@nextui-org/button";
import {Tabs, Tab} from "@nextui-org/tabs";
import {Card, CardBody} from "@nextui-org/card";
import { Select, SelectSection, SelectItem  } from "@nextui-org/select";
import { useState, useEffect } from "react";
import { useRouter } from "next/navigation";
import useReleaseList from "@/app/admin-menu/adminMenu.hook";

export const AdminMenuForm = () => {
  const [selectedTab, setSelectedTab] = useState("create");
  const [selectedReleaseId, setSelectedReleaseId] = useState<any>(0);
  const [selectedRelease, setSelectedRelease] = useState<any>(0);
  const { releaseList } = useReleaseList();
  const router = useRouter(); 

  const handleSelectionChangeTab = (key: any) => {
    setSelectedTab(key);
  };

  const handleSelectChange = (key: any) => {
    if (key.size == 0) {
      setSelectedRelease(0) 
    }   
    else { 
      setSelectedRelease(key);
    };
  };

  const handleCreatePress = () => {
    router.push("/create-release"); 
  };

  const handleEditPress = (id: number) => {
    router.push(`/edit-release/${id}`);
  };

  useEffect(() => {
    setSelectedReleaseId(selectedRelease.currentKey); 
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
                  onPress={handleCreatePress}
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
                        {releaseList.map((release) => (
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
                        isDisabled={selectedRelease === 0}
                        onPress={() => handleEditPress(selectedReleaseId)}
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