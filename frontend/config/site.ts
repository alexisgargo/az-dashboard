export type SiteConfig = typeof siteConfig;

export const siteConfig = {
  name: "Release Dashboard",
  description: "Autozone Dashboard for viewing releases.",
  navItems: [
    {
      label: "Home",
      href: "/",
    },
    {
      label: "Docs",
      href: "/docs",
    },
    {
      label: "Pricing",
      href: "/pricing",
    },
    {
      label: "Blog",
      href: "/blog",
    },
    {
      label: "About",
      href: "/about",
    },
    {
      label: "Release",
      href: "/release",
    },
    {
      label: "Releases",
      href: "/releases-dashboard",
    }
  ]
};
