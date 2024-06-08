import { AdminMenuForm } from "@/components/releaseManagement/adminMenuForm";
import {Spacer} from "@nextui-org/spacer";

export default function adminMenuPage() {
    return (
        <section className="flex flex-col items-center justify-center gap-4 py-8 md:py-10">
            <div className="flex flex-col gap-4 justify-center">
                <div>
                <h1  className="text-3xl font-bold text-center">
                    Admin Menu
                </h1>
                </div>
                <Spacer y={10} />
                <div>
                <AdminMenuForm />
                </div>
            </div>
        </section>
    );
}