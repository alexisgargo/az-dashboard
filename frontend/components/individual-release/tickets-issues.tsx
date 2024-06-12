import { Card, CardBody, CardHeader } from "@nextui-org/card";
import { Chip } from "@nextui-org/chip";
import { Button } from "@nextui-org/button";
import { useRouter } from "next/navigation";

export default function TicketsIssuesCard(
    props: Readonly<{
        ticketsCount: number;
        bugsCount: number;
        releaseId: number;
        date: string;
    }>
) {
    const router = useRouter();

    const onRedirectBugs = (id: number, date: string) => {
        router.push(`/bug-table/${id}/${date}`);
    };

    const onRedirectIssues = (id: number, date: string) => {
        router.push(`/issue-table/${id}/${date}`);
    };

    return (
        <div style={{ padding: "30px" }}>
            <Card className="w-64">
                <CardHeader className="flex justify-center">
                    <h1 className="text-xl">View Issues and Bugs</h1>
                </CardHeader>
                <CardBody>
                    <Button
                        variant="light"
                        className="flex justify-between py-2"
                        onPress={() =>
                            onRedirectIssues(props.releaseId, props.date)
                        }
                    >
                        <h1 className="text-small">Issues list</h1>
                        <div>
                            <Chip color="primary">{props.ticketsCount}</Chip>
                        </div>
                    </Button>

                    <Button
                        variant="light"
                        className="flex justify-between py-2"
                        onPress={() =>
                            onRedirectBugs(props.releaseId, props.date)
                        }
                    >
                        <h1 className="text-small">Bugs list</h1>
                        <div>
                            <Chip color="primary">{props.bugsCount}</Chip>
                        </div>
                    </Button>
                </CardBody>
            </Card>
        </div>
    );
}
