CRM Using JBoss Seam3
===============

Overview
--------

It's a final year project, released in May 2012. We have implemented a small CRM to manage users and commercial agents.
The goal of this project is the use of JavaEE Frameworks: Maven, [Seam 3], JSF2, CDI, JPA, ...

[Seam 3]:http://seamframework.org/Seam3


Project structure
-----------------

This project's (parent maven pom) divided to three projects:

1. ```CRM_PFA-Model```: Contains all persistence models
2. ```CRM_PFA-Service```: Contains our services (Dao in other terms), depend on 1
3. ```CRM_PFA-Web```: Contains JSF application, depend on 1 and 2

