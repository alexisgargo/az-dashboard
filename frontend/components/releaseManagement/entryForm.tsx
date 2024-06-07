import { release } from "@/app/release/release.types";
import { Input } from "@nextui-org/input";
import { useEffect } from "react";

export interface EntryFormProps {
    label: string;
    type: string;
    release: release;
    attribute: keyof release;
    setChanges: (release: release) => void;
    required?: boolean;
}

export const EntryForm: React.FC<EntryFormProps> = (props) => {
    return (
        <div className="w-full">
            <Input
                isRequired={props.required}
                label={props.label}
                type={props.type}
                fullWidth
                className="w-full"
                placeholder={
                    props.release[props.attribute]
                        ? props.release[props.attribute].toString()
                        : props.type === "date"
                        ? "YYYY-MM-DD"
                        : ""
                }
                onValueChange={(e) => {
                    props.setChanges({
                        ...props.release,
                        [props.attribute]: e,
                    });
                }}
            />
        </div>
    );
};
