import { EditReleaseForm } from "@/components/releaseManagement/editReleaseForm";

export default function editReleasePage({params}: any) {
    return (
        <div className="w-full">
            <EditReleaseForm id={params.id} />
        </div>
    );
}
