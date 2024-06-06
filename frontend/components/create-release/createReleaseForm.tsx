"use client";
import { EntryForm } from "./entryForm";
import { Divider } from "@nextui-org/divider";
import { Checkbox } from "@nextui-org/checkbox";
import { useState, useEffect } from "react";
import { release, engineers} from "@/app/create-release/release.types";
import { Select, SelectSection, SelectItem } from "@nextui-org/select";
import { Button } from "@nextui-org/button";
import { getEngineers, postCreateRelease } from "@/app/create-release/create-release.api";


export const CreateReleaseForm = () => {
    const forms = [
        { label: "Release name", type: "text", attribute: "name" },
        { label: "Release version", type: "text", attribute: "version" },
        { label: "Release goal", type: "text", attribute: "goal" },
        { label: "Release notes", type: "text", attribute: "release_note" },
        { label: "Code cutoff", type: "date", attribute: "code_cutoff" },
        { label: "Release date", type: "date", attribute: "init_release_date" },
    ];

    const [engineer,setEngineers] = useState<engineers>([
        {
            id_engineer: 0,
            name: "-",
        }, 
    ]);

    const [release, setRelease] = useState<release>({
        id_release: 0,
        name: "",
        version: "",
        engineer: 1,
        code_cutoff: "2024-02-02",
        init_release_date: "2024-02-02",
        curr_release_date: "2024-02-02",
        is_hotfix: false,
        status: "asdf",
        is_rollback: false,
        creation_date: "2024-02-02",
        admin: 1,
        last_modification_date: "2024-02-02",
        release_note: "",
    });

    useEffect(() => {
        console.log(release);
        
    }, [release]);

    const fetchEngineers = async () => {
        setEngineers(await getEngineers());
    };

    useEffect(() => {
        fetchEngineers();

    }, []);


    return (
        <div className="w-full">
            {forms.map((form) => (
                <div className="w-full" key={form.attribute}>
                    <Divider className="my-4" />
                    <EntryForm
                        required
                        label={form.label}
                        type={form.type}
                        release={release}
                        attribute={form.attribute}
                        setChanges={setRelease}
                    />
                </div>
            ))}

            <Divider className="my-4" />

            <Select
                label="Engineer"
                fullWidth
                className="w-full"
                onChange={(e) => {
                    setRelease({...release,engineer:parseInt(e.target.value),      
                    });              
                }}
            >
                <SelectSection>
                    {engineer.map((engineer) => (
                        <SelectItem key={engineer.id_engineer} value={engineer.name}>
                            {engineer.name}
                        </SelectItem>
                    ))}
                </SelectSection>
            </Select>

            <Divider className="my-4" />

            <Checkbox
                checked={release.is_hotfix}
                onChange={(e) =>
                    setRelease({ ...release, is_hotfix: e.target.checked })
                }
            >
                hotfix?
            </Checkbox>

            <Divider className="my-4" />

            <Button
                onClick={() => {
                    postCreateRelease(release);
                }}
            >Save
            </Button>
        </div>
    );
};
