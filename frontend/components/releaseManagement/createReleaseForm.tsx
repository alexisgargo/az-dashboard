"use client";

import { EntryForm } from "./entryForm";
import { Divider } from "@nextui-org/divider";
import { Checkbox } from "@nextui-org/checkbox";
import { useState, useEffect } from "react";
import { release } from "@/app/release/release.types";
import { Select, SelectSection, SelectItem } from "@nextui-org/select";
import { Button } from "@nextui-org/button";

export const CreateReleaseForm = () => {
    const forms = [
        { label: "Release name", type: "text", attribute: "name" },
        { label: "Release version", type: "text", attribute: "version" },
        // {
        //     label: "Release description",
        //     type: "text",
        //     attribute: "description",
        // },
        { label: "Release goal", type: "text", attribute: "goal" },
        { label: "Release notes", type: "text", attribute: "release_note" },
        { label: "Code cutoff", type: "date", attribute: "code_cutoff" },
        { label: "Release date", type: "date", attribute: "init_release_date" },
    ];

    const engineers = [
        {
            id: 2,
            name: "John Doe",
        },
        {
            id: 1,
            name: "Jane Doe",
        },
    ];

    const [release, setRelease] = useState<release>({
        id_release: 0,
        name: "",
        version: "",
        engineer: { name: "", id: 0 },
        code_cutoff: "",
        init_release_date: "",
        curr_release_date: "",
        is_hotfix: false,
        status: "",
        is_rollback: false,
        creation_date: "",
        admin: {
            admin_name: "hola",
            admin_password: "123",
            creation_date: "1/1/2000",
        },
        last_modification_date: "",
        release_note: "",
    });

    useEffect(() => {
        console.log(release);
    }, [release]);

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
                    setRelease({
                        ...release,
                        engineer: {
                            ...release.engineer,
                            id: parseInt(e.target.value),
                        },
                    });
                }}
            >
                <SelectSection>
                    {engineers.map((engineer) => (
                        <SelectItem key={engineer.id} value={engineer.name}>
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
                    console.log(release);
                }}
            >
                Save
            </Button>
        </div>
    );
};
