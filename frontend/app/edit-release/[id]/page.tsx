import { EditReleaseForm } from "@/components/edit-release/editReleaseForm";

export default function editReleasePage({params}: any) {
    return (
        <div className="w-full">
            <EditReleaseForm id={params.id} />
        </div>
    );
}
