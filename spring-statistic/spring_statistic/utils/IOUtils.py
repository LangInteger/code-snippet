"""
File IO related helper functions
"""
import errno
import json
import os


# Taken from https://stackoverflow.com/a/600612/119527
def mkdirs(path):
    try:
        os.makedirs(path)
    except OSError as exc:
        # Python >2.5
        if exc.errno == errno.EEXIST and os.path.isdir(path):
            pass
        else:
            raise


def write(content, output_path):
    mkdirs(os.path.dirname(output_path))
    with open(output_path, 'w', encoding='utf-8') as result:
        result.write(content)
        result.close()


def write_as_json_to_file(obj, output_path):
    """
    Convert obj to a formatted json and write it to output_path
    :param obj: original data
    :param output_path: path to write formatted json
    """
    content = json.dumps(obj, ensure_ascii=False, indent=4, sort_keys=True)
    write(content, output_path)


def write_to_file(content, output_path):
    """
    Write string to output_path
    :param content: data to be written
    :param output_path: path to write formatted json
    """
    write(content, output_path)


def absolute_path(relative_path):
    """
    Building a path absolute to the os in presenting of the giving relative path
    This method is limited to located in {project_root_dir}/spring_statistic/util for folder level's sake
    :return: absolute path
    """

    package_dir = os.path.dirname(os.path.dirname(os.path.dirname(os.path.abspath(__file__))))
    return os.path.join(package_dir, relative_path)
