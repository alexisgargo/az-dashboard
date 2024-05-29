export default function LoginLayout({
	children,
  }: {
	children: React.ReactNode;
  }) {
	return (
	  <section className="flex flex-col items-center justify-center">
		{children}
	  </section>
	);
  }
  