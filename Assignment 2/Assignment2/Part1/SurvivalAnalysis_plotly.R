Sys.setenv("plotly_username"="sarthak12")
Sys.setenv("plotly_api_key"="ww0uv901l0")


library(OIsurv)
library(devtools)
library(plotly)
library(IRdisplay)
library(ggplot2)
library(survival)
library(GGally)


data(cancer, package = "survival")
summary(cancer)
head(cancer)
attach(cancer)

sf.cancer <- survfit(Surv(time, status) ~ 1, data = cancer)
p <- ggsurv(sf.cancer)
ggplotly(p)
plotly_POST(p, "Survival vs Time")

sf.sex <- survfit(Surv(time, status) ~ sex, data = cancer)
pl.sex <- ggsurv(sf.sex)
ggplotly(pl.sex)

plotly_POST(pl.sex, "Gender wise - Survival vs Time")
