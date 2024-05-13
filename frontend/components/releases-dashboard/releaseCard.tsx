import { Card, CardBody, CardHeader } from "@nextui-org/card";
import { CircularProgress } from "@nextui-org/progress";
import { Checkbox } from "@nextui-org/checkbox";
import { useRouter } from "next/navigation";

export default function ReleaseCard() {
  const router = useRouter()
  const is_hotfix = true
  const is_rollback = true

  const onRedirect = () => {
    router.push('/release')
  }

  return (
    <Card isPressable onPress={click => onRedirect()}>
      <CardHeader className="justify-center">
        <h1 className="text-xl font-bold">Release Title</h1>
      </CardHeader>
      <CardBody className="flex flex-row justify-around space-x-5">
        <CircularProgress
          label="Release Progress"
          color="success"
          value={69}
          minValue={0}
          maxValue={100}
          classNames={{ svg: "w-32 h-32", value: "text-3xl font-semibold" }}
          showValueLabel={true}
        />
        <div>
          <p>Release id: asdf1.2</p>
          <p>Release status: on track</p>
          <p>Code-cutoff date: yesterday</p>
          <p>Release date: tomorrow</p>
          <p>Release engineer: Lion</p>
          <div className="flex flex-row gap-x-2">
            <Checkbox isSelected={is_hotfix} isDisabled color="warning">Hotfix</Checkbox>
            <Checkbox isSelected={is_rollback} isDisabled color="danger">Rollback</Checkbox>
          </div>
          <p>Notes: this is pretty cool</p>
        </div>
      </CardBody>
    </Card>
  )
}