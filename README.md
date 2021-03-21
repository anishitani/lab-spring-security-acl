# lab-spring-security-acl

Experiments with Spring Security ACL 

## Setup

This is a simple setup with an Application, a Database for the Application and an IdP (Keycloak).

We'll use the IdP to authenticate the Users and generate the Access Token. It's not in the scope of this experiment to create the most secure environment, therefore it's not compliant with most security best practices.

This experiment focuses on ACL properties of the Spring Security framework.

## Experiment

Given a simple setup we'll try the following:

1. Protect resources from non owners
1. Avoid resource creation on other user behalf
1. Allow common resource to be accessible by all users