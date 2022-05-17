import argparse

from profiler.profiler import Profiler


# to run tests:
def run_profiler():
    parser = argparse.ArgumentParser()
    parser.add_argument('-m', '--module')
    parser.add_argument('-f', '--function')
    parser.add_argument('--function-package')

    args = parser.parse_args()

    profiler = Profiler(args.module)
    profiler.profile(args.function_package, args.function)


if __name__ == '__main__':
    run_profiler()
