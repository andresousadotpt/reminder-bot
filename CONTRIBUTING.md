# Contributing to examples

I want to make contributing to this project as easy and transparent as
possible.

## Pull Requests

I actively welcome your pull requests.

If you're new, we encourage you to take a look at issues tagged with [good first issue](https://github.com/pytorch/examples/issues?q=is%3Aissue+is%3Aopen+label%3A%22good+first+issue%22)

### For new examples

0. Create a GitHub issue proposing a new example and make sure it's substantially different from an existing one.
1. Fork the repo and create your branch from `main`.
2. If you've added code that should be tested, add tests to `src/test`.
3. Ensure your test passes locally.

## For bug fixes

1. Fork the repo and create your branch from `main`.
2. Make your code change.
3. First, install all dependencies with `mvn dependency:resolve`.
4. Then, make sure that `mvn test` passes locally.
5. Address any feedback in code review promptly.

## Issues

We use GitHub issues to track public bugs. Please ensure your description is
clear and has sufficient instructions to be able to reproduce the issue.

## License

By contributing to examples, you agree that your contributions will be licensed
under the LICENSE file in the root directory of this source tree.