"use client";

import { EntryForm } from "./entryForm";
import { Divider } from "@nextui-org/divider";
import { Checkbox } from "@nextui-org/checkbox";
import { Button } from "@nextui-org/button";
import { useState, useEffect } from "react";
import { release } from "@/app/release/release.types";
import { Select, SelectSection, SelectItem } from "@nextui-org/select";
import { getRelease } from "@/app/release/release.api";
import { putRelease } from "@/app/edit-release/edit-release.api";
import { getEngineers } from "@/app/edit-release/edit-release.api";
import { engineer } from "@/app/edit-release/release.types";

export const EditReleaseForm = (props: { id: number }) => {
    const forms: {
        label: string;
        type: string;
        attribute: keyof release;
    }[] = [
        { label: "Release name", type: "text", attribute: "name" },
        { label: "Release version", type: "text", attribute: "version" },
        { label: "Release notes", type: "text", attribute: "release_note" },
        { label: "Code cutoff", type: "date", attribute: "code_cutoff" },
        { label: "Release date", type: "date", attribute: "curr_release_date" },
    ];

    const [engineers, setEngineers] = useState<engineer[]>([]);

    const [release, setRelease] = useState<release>();

    const fetchRelease = async () => {
        setRelease(await getRelease(props.id));
        setEngineers(await getEngineers());
    };

    useEffect(() => {
        fetchRelease();
    }, []);

    if (release == undefined) {
        return <div>Searching...</div>;
    }

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
                defaultSelectedKeys={release.engineer.name}
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
                    putRelease(release, props.id);
                }}
            >
                Save
            </Button>
        </div>
    );
};
