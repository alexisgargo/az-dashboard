import YearMetrics from "./year-metrics";


export default function DashboardBottom(prop: { metrics: number[] }) {
  return (
    <div className="grid grid-cols-4 absolute bottom-5">
      <div className="col-span-2">
        <YearMetrics metrics={prop.metrics} />
      </div>
    </div>
  )
}