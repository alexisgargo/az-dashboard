import { CircularProgress } from "@nextui-org/progress";

export default function ProgressComponent(prop: {totalProgress: number,name:any}) {
  

	return (
	  <div>
		<CircularProgress
            size="lg"
            classNames={ {svg: "w-64 h-64",value: "text-3xl font-semibold text-white", }}
            value={prop.totalProgress}
            color={prop.totalProgress > 75 ? 'success' : (prop.totalProgress > 50 ? 'warning' : 'danger') }
            label={prop.name}
            showValueLabel={true}
          >
		  </CircularProgress>
	  </div>
	);
  }