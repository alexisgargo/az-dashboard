import { Card, CardBody, CardHeader } from "@nextui-org/card";
import { Chip } from "@nextui-org/chip";
import { Button } from "@nextui-org/button";

export default function TicketsIssuesCard(props: { ticketsCount: number, bugsCount: number }) {
  return (
    <Card className="w-64">
      <CardHeader className="flex justify-center">
        <h1 className="text-xl">View Tickets and Bugs</h1>
      </CardHeader>
      <CardBody>
        <Button variant="light" className="flex justify-between py-2">
          <h1 className="text-small">Tickets list</h1>
          <div>
            <Chip color="primary">{props.ticketsCount}</Chip>
          </div>
        </Button>

        <Button variant="light" className="flex justify-between py-2">
          <h1 className="text-small">Bugs list</h1>
          <div>
            <Chip color="primary">{props.bugsCount}</Chip>
          </div>
        </Button>
      </CardBody>
    </Card>
  )
}