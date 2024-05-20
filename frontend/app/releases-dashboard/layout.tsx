export default function ReleaseLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <section className="flex items-center justify-center">
      {children}
    </section>
  );
}
