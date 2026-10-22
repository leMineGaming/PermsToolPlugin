# PermsTool Plugin
![test](https://badgen.net/badge/status/stable/green?icon=github)
![test2](https://badgen.net/badge/latest/v1.4/blue?icon=version)

![avast](https://i.ibb.co/pr2hn5z/Avast-Safe2.png)

| Architecture  | Support |
| ------------- | ------------- |
| PaperMC  | ✅  |
| Folia  | ✅  |
| Spigot  | ✅  |
| Sponge  | ❌  |
| Forge  | ❌  |
| NeoForge  | ❌  |
| BungeeCord  | ❌  |
| Bukkit Legacy  | ❌  |

| Version          | Support |
|------------------|---------|
| 26.3             | ✅       |
| 26.1 - 26.2      | ✅       |
| 1.21.1 - 1.21.11 | ✅       |
| 1.20.X and older | ❌       |

A single jar supports every version from 1.21.1 up to 26.3. It is compiled against the 1.21.1 API and only uses APIs that exist there, so newer servers (which use the calendar versioning introduced in 2026, e.g. `26.1`, `26.2`, `26.3`) load it unchanged.

The same jar runs on Folia (tested with Folia 26.1.2 and 26.2). `plugin.yml` declares `folia-supported: true`, which Folia requires, and `/admin` only touches the player from the region thread that already owns them, so no Folia-specific scheduler calls are needed. Paper and Spigot ignore the Folia flag.

# Installation
1. download from releases or build yourself using maven
2. add to plugins directory
3. Run your Server

Make sure you have installed LuckPerms!
