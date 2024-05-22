"use client";

import { EntryForm } from "./entryForm";
import { Divider } from "@nextui-org/divider";
import { Checkbox } from "@nextui-org/checkbox";
import { useState, useEffect } from "react";
import { release } from "@/app/release/release.types";
import { Select, SelectSection, SelectItem } from "@nextui-org/select";
import { getRelease } from "@/app/release/release.api";
import { Button } from "@nextui-org/button";

export const EditReleaseForm = (props: { id: number }) => {
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
        { label: "Release date", type: "date", attribute: "curr_release_date" },
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
            admin_name: "",
            admin_password: "",
            creation_date: "",
        },
        last_modification_date: "",
        release_note: "",
    });

    const fetchRelease = async () => {
        setRelease(await getRelease(props.id));
    };

    useEffect(() => {
        fetchRelease();
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
                    console.log(release);
                }}
            >
                Save
            </Button>
        </div>
    );
};
