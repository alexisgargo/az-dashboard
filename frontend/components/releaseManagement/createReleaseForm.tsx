"use client";

import { EntryForm } from "./entryForm";
import { Divider } from "@nextui-org/divider";
import { Checkbox } from "@nextui-org/checkbox";
import { useState, useEffect } from "react";
import { release } from "@/app/release/release.types";
import { Select, SelectSection, SelectItem } from "@nextui-org/select";
import { Button } from "@nextui-org/button";
import {
    getEngineers,
    postCreateRelease,
} from "@/app/create-release/create-release.api";
import { engineer } from "@/app/edit-release/release.types";

export const CreateReleaseForm = () => {
    const forms: {
        label: string;
        type: string;
        attribute: keyof release;
    }[] = [
        { label: "Release name", type: "text", attribute: "name" },
        { label: "Release version", type: "text", attribute: "version" },
        { label: "Release notes", type: "text", attribute: "release_note" },
        { label: "Code cutoff", type: "date", attribute: "code_cutoff" },
        { label: "Release date", type: "date", attribute: "init_release_date" },
    ];

    const [engineers, setEngineers] = useState<engineer[]>([]);

    // TODO: admin loggeado
    const [release, setRelease] = useState<release>({
        id_release: 0,
        name: "",
        version: "",
        engineer: { name: "", id_engineer: 0 },
        code_cutoff: "",
        init_release_date: "",
        curr_release_date: "",
        is_hotfix: false,
        status: "On Time",
        is_rollback: false,
        creation_date: new Date().toJSON().slice(0, 10),
        admin: {
            id_admin: 1,
            username: "",
            adminPassword: "",
            creation_date: "",
        },
        last_modification_date: new Date().toJSON().slice(0, 10),
        release_note: "",
    });

    useEffect(() => {
        const fetchEngineers = async () => {
            setEngineers(await getEngineers());
        };
        fetchEngineers();
    }, []);

    useEffect(() => {
        setRelease({
            ...release,
            curr_release_date: release.init_release_date,
        });
    }, [release.init_release_date]);

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
                            id_engineer: parseInt(e.target.value),
                        },
                    });
                }}
            >
                <SelectSection>
                    {engineers.map((engineer) => (
                        <SelectItem
                            key={engineer.id_engineer}
                            value={engineer.name}
                        >
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
            >
                Save
            </Button>
        </div>
    );
};
