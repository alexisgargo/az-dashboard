import { CreateReleaseForm } from "@/components/create-release/createReleaseForm";

export default function createReleasePage() {
    return (
        <div className="w-full">
            <h1 className="text-3xl font-bold text-center">
                Create a new release
            </h1>
            <CreateReleaseForm />
        </div>
    );
}
