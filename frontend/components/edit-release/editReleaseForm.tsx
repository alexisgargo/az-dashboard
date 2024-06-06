"use client";

import { EntryForm } from "../edit-release/entryForm";
import { Divider } from "@nextui-org/divider";
import { Checkbox } from "@nextui-org/checkbox";
import { useState, useEffect } from "react";
import { Select, SelectSection, SelectItem } from "@nextui-org/select";
import { Button } from "@nextui-org/button";

// import { release } from "@/app/release/release.types";
// import { getRelease } from "@/app/release/release.api";

import { release,release1,engineers } from "@/app/edit-release/release.types";
import { getRelease, putRelease } from "@/app/edit-release/edit-release.api";

import { getEngineers } from "@/app/create-release/create-release.api";

export const EditReleaseForm = (props: { id: number }) => {
    const forms = [
        { label: "Release name", type: "text", attribute: "name" },
        { label: "Release version", type: "text", attribute: "version" },
        { label: "Release goal", type: "text", attribute: "goal" },
        { label: "Release notes", type: "text", attribute: "release_note" },
        { label: "Code cutoff", type: "date", attribute: "code_cutoff" },
        { label: "Release date", type: "date", attribute: "curr_release_date" },
    ];

    const [engineer,setEngineers] = useState<engineers>([
        {
            id_engineer: 0,
            name: "-",
        }
    ]);

    const [release, setRelease] = useState<release>({
        id_release: 0,
        name: "",
        version: "",
        engineer: 0,
        code_cutoff: "",
        init_release_date: "",
        curr_release_date: "",
        is_hotfix: false,
        status: "",
        is_rollback: false,
        creation_date: "",
        admin: 1,
        last_modification_date: "",
        release_note: "",
    });

    function transformApi(response: release1): release {
        return {
            id_release: response.id_release,
            name: response.name,
            version: response.version,
            engineer: response.engineer.id_engineer,
            code_cutoff: response.code_cutoff,
            init_release_date: response.init_release_date,
            curr_release_date: response.curr_release_date,
            is_hotfix: response.is_hotfix,
            status: response.status,
            is_rollback: response.is_rollback,
            creation_date: response.creation_date,
            admin: 0,
            last_modification_date: response.last_modification_date,
            release_note: response.release_note
        };
    }
    const fetchRelease = async () => {
  
        setRelease(transformApi(await getRelease(props.id)));
    };

    const fetchEngineers = async () => {
        setEngineers(await getEngineers());
    };

    useEffect(() => {
        fetchRelease();
        fetchEngineers();
    }, []);

    useEffect(() => {
        console.log(release);
    }, [release]);

    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">
                {`${release.name} ${release.version}`}
            </h1>

            {forms.map((form) => (
                <div className="w-full" key={form.attribute}>
                    <Divider className="my-4" />
                    <EntryForm
                        label={form.label}
                        type={form.type}
                        release={release}
                        attribute={form.attribute}
                        setChanges={setRelease}
                    />
                </div>
            ))}

            <p className="text-lg font-semiboldt">
                Initial release date: {release.init_release_date}
            </p>

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

            {Date.now() >= Date.parse(release.curr_release_date) && (
                <div>
                    <Divider className="my-4" />
                    <Checkbox
                        checked={release.is_rollback}
                        onChange={(e) =>
                            setRelease({
                                ...release,
                                is_rollback: e.target.checked,
                            })
                        }
                    >
                        rollback?
                    </Checkbox>
                </div>
            )}

            <Divider className="my-4" />
            <Button
                onClick={() => {
                    // TODO: call function to save release sending release object
                    putRelease(release,props.id);
                }}
            >
                Save
            </Button>
        </div>
    );
};
        