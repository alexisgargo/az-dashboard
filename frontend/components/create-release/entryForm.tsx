import { release } from "@/app/create-release/release.types";
import { Input } from "@nextui-org/input";
import { useEffect } from "react";

export interface EntryFormProps {
    label: string;
    type: string;
    release: release;
    attribute: string;
    setChanges: (release: release) => void;
    required?: boolean;
}

export const EntryForm: React.FC<EntryFormProps> = (props) => {
    /*
        TODO: calendario 
        const today = new Date();
        const todayString = today.toISOString().split("T")[0];

        defaultValue={props.type === "date" ? todayString : ""}
    */
    return (
        <div className="w-full">
            <Input
                isRequired={props.required}
                label={props.label}
                type={props.type}
                fullWidth
                className="w-full"
                placeholder={
                    props.release ? props.type === "date" ? "YYYY-MM-DD" : "" : ""
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
